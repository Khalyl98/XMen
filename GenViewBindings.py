#!/usr/bin/python3
# -*- coding: utf-8 -*-

# Ce script génère les classes ViewBinding correspondant aux
# layouts XML présents dans le projet.
# NB: il n'y a aucun contrôle des erreurs, alors méfiance

# Pierre Nerzic, pierre.nerzic@univ-rennes1.fr, 9 février 2021, 25 mars 2021.

# INSTALLATION : Il faut placer et lancer ce script dans le dossier du projet,
# c'est à dire au même niveau que le dossier app.

# LANCEMENT : il suffit de double cliquer dessus ou de l'ouvrir dans un IDE
# Python (Idle, Spyder) et de l'exécuter


from xml.dom import minidom     # lecture de fichiers XML
import os                       # fonctions pour gérer les fichiers et dossiers
import glob                     # expansion de motifs sur les fichiers


# extrait le package du Manifeste
def getPackage():
    """retourne le package du projet, tiré du manifeste"""
    with minidom.parse('app/src/main/AndroidManifest.xml') as manifest:
        racine = manifest.getElementsByTagName('manifest')[0]
        return racine.getAttribute('package')


# cherche les vues ayant un identifiant dans le layout
def collectViews(layout, views):
    """récupère les identifiants et types de toutes les vues qui ont un attribut android:id, return dictionnaire[id]=type"""
    # parcourir tous les Node enfants du layout
    for child in layout.childNodes:
        # ignorer le Node si c'est pas un élément
        if child.nodeType != child.ELEMENT_NODE: continue
        # s'intéresser à ceux qui ont un identifiant android
        if child.hasAttribute('android:id'):
            # il y a un identifiant android
            view_id = child.getAttribute('android:id').replace('@+id/', '')
            # enregistrer la balise en tant que type pour cette vue
            views[view_id] = child.nodeName
        # appel récursif pour traiter les éléments enfants de celui-ci
        collectViews(child, views)
    # résultat final : toutes les vues se sont accumulées dedans
    return views


# traite un layout => crée la classe ViewBinding
def processLayoutFile(filename, package):
    """crée la classe de ViewBinding pour ce layout"""
    # récupérer la liste des vues (id et type) du layout
    with minidom.parse(filename) as layout:
        # chercher la racine du layout
        racine = layout.documentElement.nodeName
        # layout = document XML entier, on y cherche les vues avec identifiant
        views = collectViews(layout, dict())
    # obtenir le nom de base sans l'extension
    dirname,basename = os.path.split(filename)
    basename, ext = os.path.splitext(basename)
    # mettre ce nom en CamelCase pour en faire une classe Java
    # FIXME c'est ça qui peut créer une erreur si le layout a un nom bizarre
    classname = basename.title().replace('_', '') + 'Binding'
    # infos
    print(f"Traitement de {filename} => {classname} dans le package {package}")
    for view_id, view_type in views.items():
        print(f"    {view_type} {view_id};")
    # chemin des sources à générer
    dirname = package.replace('.', '/')
    # créer le source
    with open(f'app/src/main/java/{dirname}/{classname}.java', 'w', encoding='utf-8') as outfile:
        outfile.write(f'package {package};\n')
        outfile.write('\n')
        outfile.write('// généré par GenViewBindings.py\n')
        outfile.write('// Pierre Nerzic, pierre.nerzic@univ-rennes1.fr\n')
        outfile.write('\n')
        outfile.write('import android.annotation.SuppressLint;\n')
        outfile.write('import android.view.LayoutInflater;\n')
        outfile.write('import android.view.View;\n')
        outfile.write('import android.view.ViewGroup;\n')
        outfile.write('\n')
        outfile.write('import androidx.annotation.NonNull;\n')
        outfile.write('import androidx.annotation.Nullable;\n')
        outfile.write('\n')
        outfile.write('// FIXME imports manquants pour les vues\n')
        outfile.write('import android.widget.*;\n')
        outfile.write('\n')
        outfile.write('@SuppressWarnings("unused")\n')
        outfile.write(f'public class {classname}\n')
        outfile.write('{\n')
        outfile.write('    // identifiant du layout associé à ce View Binding\n')
        outfile.write(f'    private static final int ID_LAYOUT = R.layout.{basename};\n')
        outfile.write('\n')
        outfile.write('    // vue racine du layout, voir getRoot()\n')
        outfile.write(f'    private @NonNull final {racine} root;\n')
        outfile.write('\n')
        outfile.write('    // objets Java associés aux vues avec identifiants\n')
        for view_id, view_type in views.items():
            outfile.write(f'    public @NonNull final {view_type} {view_id};\n')
        outfile.write('\n')
        outfile.write('    /**\n')
        outfile.write('     * constructeur privé : utiliser inflate et bind\n')
        outfile.write('     * @param root vue racine à associer à this\n')
        outfile.write('     */\n')
        outfile.write(f'    private {classname}(@NonNull {racine} root)\n')
        outfile.write('    {\n')
        outfile.write('        this.root = root;\n')
        outfile.write('        // récupérer les vues du layout\n')
        for view_id, view_type in views.items():
            outfile.write(f'        {view_id} = root.findViewById(R.id.{view_id});\n')
        outfile.write('    }\n')
        outfile.write('\n')
        outfile.write('    /**\n')
        outfile.write(f'     * instancie un nouveau layout et retourne un nouveau {classname}\n')
        outfile.write('     * @param layoutInflater expanseur de layout\n')
        outfile.write(f'     * @return nouvelle instance de {classname}\n')
        outfile.write('     */\n')
        outfile.write('    @SuppressLint("InflateParams")\n')
        outfile.write('    @NonNull\n')
        outfile.write(f'    public static {classname} inflate(@NonNull LayoutInflater layoutInflater)\n')
        outfile.write('    {\n')
        outfile.write(f'        return new {classname}(({racine}) layoutInflater.inflate(ID_LAYOUT, null, false));\n')
        outfile.write('    }\n')
        outfile.write('\n')
        outfile.write('    /**\n')
        outfile.write(f'     * instancie un nouveau layout et retourne un nouveau {classname}\n')
        outfile.write('     * @param layoutInflater expanseur de layout\n')
        outfile.write('     * @param parent vue parente à laquelle rattacher celle-ci, peut être null\n')
        outfile.write('     * @param attachToParent true s\'il faut faire un attachement au parent\n')
        outfile.write(f'     * @return nouvelle instance de {classname}\n')
        outfile.write('     */\n')
        outfile.write('    @NonNull\n')
        outfile.write(f'    public static {classname} inflate(\n')
        outfile.write('         @NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent, boolean attachToParent)\n')
        outfile.write('    {\n')
        outfile.write(f'        return new {classname}(({racine}) layoutInflater.inflate(ID_LAYOUT, parent, attachToParent));\n')
        outfile.write('    }\n')
        outfile.write('\n')
        outfile.write('    /**\n')
        outfile.write(f'     * associe un nouveau {classname} à une vue existante\n')
        outfile.write('     * @param root vue racine à associer\n')
        outfile.write(f'     * @return nouvelle instance de {classname}\n')
        outfile.write('     */\n')
        outfile.write('    @NonNull\n')
        outfile.write(f'    public static {classname} bind(@NonNull {racine} root)\n')
        outfile.write('    {\n')
        outfile.write(f'        return new {classname}(root);\n')
        outfile.write('    }\n')
        outfile.write('\n')
        outfile.write('    /**\n')
        outfile.write('     * retourne la vue racine du layout attaché à ce ViewBinding\n')
        outfile.write('     * @return View racine du layout\n')
        outfile.write('     */\n')
        outfile.write(f'    @NonNull public {racine} getRoot()\n')
        outfile.write('    {\n')
        outfile.write('        return root;\n')
        outfile.write('    }\n')
        outfile.write('}\n')


# traite tous les layouts
def processLayoutFolder():
    """traite chaque fichier xml du dossier des layouts"""
    package = getPackage()
    for filename in glob.iglob('app/src/main/res/layout/*.xml'):
        processLayoutFile(filename, package)


## point d'entrée dans le logiciel
processLayoutFolder()


