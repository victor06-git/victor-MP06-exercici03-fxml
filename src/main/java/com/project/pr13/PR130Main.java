package com.project.pr13;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe principal que gestiona la lectura i el processament de fitxers XML per
 * obtenir dades de persones.
 *
 * Aquesta classe s'encarrega de llegir un fitxer XML que conté informació de
 * persones, processar-lo i mostrar les dades formatades per consola.
 */
public class PR130Main {

    private final File dataDir;

    /**
     * Constructor de la classe PR130Main.
     *
     * @param dataDir Directori on es troben els fitxers de dades.
     */
    public PR130Main(File dataDir) {
        this.dataDir = dataDir;
    }

    /**
     * Mètode principal que inicia l'execució del programa.
     *
     * @param args Arguments passats a la línia de comandament (no s'utilitzen
     * en aquest programa).
     */
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        File dataDir = new File(userDir, "data" + File.separator + "pr13");

        PR130Main app = new PR130Main(dataDir);
        app.processarFitxerXML("persones.xml");
    }

    /**
     * Processa un fitxer XML per obtenir la informació de les persones i
     * imprimir-la.
     *
     * @param filename Nom del fitxer XML a processar.
     */
    public void processarFitxerXML(String filename) {
        File inputFile = new File(dataDir, filename);
        Document doc = parseXML(inputFile);
        if (doc != null) {
            NodeList persones = doc.getElementsByTagName("persona");
            imprimirCapçaleres();
            imprimirDadesPersones(persones);
        }
    }

    /**
     * Llegeix un fitxer XML i el converteix en un objecte Document.
     *
     * @param inputFile Fitxer XML a llegir.
     * @return Document XML carregat o null si hi ha hagut un error en la
     * lectura.
     */
    public static Document parseXML(File inputFile) {
        // *************** CODI PRÀCTICA **********************/
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();

            Document doc = dbBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            return doc; // Substitueix pel teu 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void imprimirCapçaleres() {
        String[] capçalera = {"Nom", "Cognom", "Edat", "Ciutat"};

        String cabecera = String.format("%-8s %-14s %-5s %-9s", capçalera[0], capçalera[1], capçalera[2], capçalera[3]);

        System.out.println(cabecera);

        System.out.printf("%-8s %-14s %-5s %-9s%n",
                "-".repeat(8),
                "-".repeat(14),
                "-".repeat(5),
                "-".repeat(9));
    }

    public void imprimirDadesPersones(NodeList fitxerXML) {

        Integer length = fitxerXML.getLength();

        for (int i = 0; i < length; i++) {
            Node node = fitxerXML.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elm = (Element) node;

                NodeList nodeList_nom = elm.getElementsByTagName("nom");
                NodeList nodeList_cognom = elm.getElementsByTagName("cognom");
                NodeList nodeList_edat = elm.getElementsByTagName("edat");
                NodeList nodeList_ciutat = elm.getElementsByTagName("ciutat");

                String nom = nodeList_nom.item(0).getTextContent();
                String cognom = nodeList_cognom.item(0).getTextContent();
                String edat = nodeList_edat.item(0).getTextContent();
                String ciutat = nodeList_ciutat.item(0).getTextContent();

                System.out.printf("%-8s %-14s %-5s %-9s%n", nom, cognom, edat, ciutat);
            }
        }

    }
}
