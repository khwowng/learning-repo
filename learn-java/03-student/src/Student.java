import java.util.Scanner;

public class Student {

    private String nachname;
    private String vorname;
    private String matrNr;
    private String studienGruppe;
    private String mailaddresse;
    private double klausurnote;
    private double belegnote;
    private String belegAbgabeDatum;


    // getter und setter

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(String matrNr) {
        this.matrNr = matrNr;
    }

    public String getStudienGruppe() {
        return studienGruppe;
    }

    public void setStudienGruppe(String studienGruppe) {
        this.studienGruppe = studienGruppe;
    }

    public String getMailaddresse() {
        return mailaddresse;
    }

    public void setMailaddresse(String mailaddresse) {
        this.mailaddresse = mailaddresse;
    }

    public double getKlausurnote() {
        return klausurnote;
    }

    public void setKlausurnote(double klausurnote) {
        this.klausurnote = klausurnote;
    }

    public double getBelegnote() {
        return belegnote;
    }

    public void setBelegnote(double belegnote) {
        this.belegnote = belegnote;
    }

    public String getBelegAbgabeDatum() {
        return belegAbgabeDatum;
    }

    public void setBelegAbgabeDatum(String belegAbgabeDatum) {
        this.belegAbgabeDatum = belegAbgabeDatum;
    }


    // Constructor zur Übernahme
    public Student(String nachname, String vorname, String matrNr, String studienGruppe, String mailaddresse) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.matrNr = matrNr;
        this.studienGruppe = studienGruppe;
        this.mailaddresse = mailaddresse;
    }

    // DefaultConstructor
    public Student(){}

    // Berechnung der Endnote
    public double berechnenEndnote(){
        double endnote = 0.7*klausurnote + 0.3*belegnote;
        return endnote;
    }

    // überschriebene toString() Funktion
    public String toString(){
        return "Nachname: " + nachname + "\nVorname: " + vorname +
                "\nMatrikelnummer: " + matrNr + "\nStudien Gruppe: " + studienGruppe +
                "\nMailaddresse: " + mailaddresse + "\nEndnote: " + String.valueOf(berechnenEndnote()) +
                "\nBeleg Abgabe Datum: " + belegAbgabeDatum;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        if (args.length == 5) {
            Student student1 = new Student(args[0], args[1], args[2], args[3], args[4]);
            System.out.print("Daten zum Beleg: ");
            student1.belegAbgabeDatum = sc.nextLine();
            System.out.println("Klausurnote eingeben: ");
            student1.klausurnote = sc.nextDouble();
            System.out.println("Belegnote eingeben: ");
            student1.belegnote = sc.nextDouble();
            System.out.println(student1.toString());
        }
        else if (args.length == 0) {
            Student student1 = new Student(); // DefaultConstructor
        }
        else {
            System.out.println("Bitte die nötigen Argumente (Nachname, Vorname, MatrNr, StrudienGruppe und e-Mail) eingeben");
        }
    }
}
