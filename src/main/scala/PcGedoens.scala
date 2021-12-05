/*
 *  Vorüberlegungen:
 *  PC
 *   => Liste von Teil(en)
 *
 *  PcTeil:
 *  --berechnePreis()
 *  -- Namen
 *  -Einzeilteil
 *      -- Preis
 *  -Zusammengesetzte Teile
 *      -- Liste von Einzelteilen
 *      -liquidCooling
 *          --wartungsIntervall: Int (in Tagen)
 *          --kopf: Einzelteil
 *          --schlauch: Einzeilteil
 *
 *  ExternesTeil
 *  -- name
 *  -- preis
 *
 *  Setup
 *  -- Liste[ExternesTeil]
 *  -- PC (nur einmal!)
 */

object PcGedoens {

    def main(args: Array[String]): Unit = {
        // Erzeugen der Einzelteile und zusammenstecken in den PC
        val stecker: Einzelteil = Einzelteil("Stecker", 20)
        val graka: Einzelteil = Einzelteil("RTX3080", 2500)
        val cpu: Einzelteil = Einzelteil("Ryzen 9", 1020)
        //-----------
        val schlauch: Einzelteil = Einzelteil("be Quiet", 5)
        val kopf: Einzelteil = Einzelteil("be Quiet Kopf", 30)
        //-----------
        val cooler: LiquidCooling = LiquidCooling(720, kopf, schlauch)
        val lucasPC: PC = PC(
            List(stecker, graka, cpu, cooler)
        )

        // Erzeugen der Externen-Teile und ins Setup stecken
        val keyboard: ExternesTeil = ExternesTeil("Roccat Vulkan 120", 130)
        val monitor: ExternesTeil = ExternesTeil("Predator", 900)
        val switcher: ExternesTeil = ExternesTeil("Setup-Switcher", 300)

        val lucasTeuresSetup: Setup = Setup(lucasPC,List(keyboard, monitor, switcher))

        // assert(lucasTeuresSetup.berechnePreis() == 4905)
        // Bis hier hin für den Test:

        println("Preis LucasPC: " + lucasPC.berechnePreis())
        println("List(Teile von LucasPC): " + lucasPC.teileNamenListe())
        println("Teile von LucasPC: " + lucasPC.teileNamenString())
        println("Preis von LucasSetup: " + lucasTeuresSetup.berechnePreis())
        println("Tage bis WaKü-Wartung: " + cooler.tageBisWartung(50))
        println("Tage bis WaKü-Wartung: " + cooler.tageBisWartung(5000))
    }
}

/**
 * Case-Class für das Setup speichert den Computer und eine Liste an externen Teilen.
 */
case class Setup(computer: PC, rest: List[ExternesTeil]) {

    /**
     * Berechnet den Preis d. Setups: PC-Preis + Preise aller externen-Teile
     */
    def berechnePreis(): Int = {
        var result: Int = 0
        rest.foreach(externesTeil => {
            result += externesTeil.berechnePreis()
        })
        result + computer.berechnePreis()
    }
}

/**
 * Klasse für externe Teile wie Tastatur etc.
 */
case class ExternesTeil(name: String, preis: Int){
    /**
     * Gibt den Preis des Teils zurück.
     */
    def berechnePreis(): Int = {
        this.preis
    }
}

/**
 * Klasse für den PC. Besteht aus einer Liste an PC-Teilen.
 */
case class PC(teile: List[PcTeil]) {

    /*
     * [Einzelteil(Stecker,20), Einzelteil(RTX3080,2500), Einzelteil(Ryzen 9,1020)]
     *          |                           |                       |
     * ["Stecker",                   "RTX3080",             "Ryzen 9", ...]
     *
     * AllgemeinesTeil -> String
     */

    /**
     * Gibt die Liste aller Teilnamen zurück.
     */
    def teileNamenListe(): List[String] = {
        //mapping == "Abbildung"
        teile.map(teil => teil.getName())
    }

    /**
     * Gibt die Liste aller Teilnamen als String zurück.
     */
    def teileNamenString(): String = {
        //mapping == "Abbildung"
        teileNamenListe().mkString(", ")
    }

    /**
     * Berechnet den Preis des PCs.
     * @return
     */
    def berechnePreis(): Int = {
        var result: Int = 0

        teile.foreach(aktuellesTeil => {
            result += aktuellesTeil.berechnePreis()
        })

        result
    }

}

/**
 * Abstrahierung eines PC-Teils.
 */
abstract class PcTeil(name: String) {
    def berechnePreis(): Int

    /**
     * Gibt den Namen des Pc-Teils zurück.
     */
    def getName(): String = {
        this.name
    }

}

/**
 * Klasse eines Einzelteils. Besteht aus Namen und Preis und ist eine Spezialisierung (Vererbung) von PC-Teil.
 */
case class Einzelteil(nameDesEinzelteils: String, preis: Int) extends PcTeil(nameDesEinzelteils) {
    /**
     * Berechnet den Preis des Einzelteils.
     */
    override def berechnePreis(): Int = {
        this.preis
    }
}

/**
 * Klasse eines Zusammengesetzen-Teils. Besteht aus dem Namen und einer Liste an PC-Teilen. Ist eine Spezialisierung von PC-Teil.
 */
class ZusammengesetztesTeil(nameDesZuTeils: String, teile: List[PcTeil]) extends PcTeil(nameDesZuTeils) {
    /**
     * Berechnet den Preis des Einzelteils.
     */
    override def berechnePreis(): Int = {
        var result: Int = 0
        teile.foreach(aktuellesTeil => {
            aktuellesTeil match {
                case teil: Einzelteil => result += teil.berechnePreis()
                case teil: ZusammengesetztesTeil => result += teil.berechnePreis()
            }
        })

        result
    }
}

/**
 * Klasse für die Flüssigkühlung. Ist eine Erweiterung eines Zusammengesetzen-Teils.
 * Besteht aus einem Wartungsintervall, dem Kopf (Einzelteil) und dem Schlauch (Einzelteil).
 */
case class LiquidCooling(wartungsIntervall: Int, kopf: Einzelteil, schlauch: Einzelteil)
    extends ZusammengesetztesTeil("Flüssigkühlung", List(kopf, schlauch)){
    /**
     * Nimmt die Tage seit der letzten Wartung entgegen und berechnet die verbleibende Laufdauer.
     * Gibt einen String mit einer Wartungsaufforderung zurück.
     */
    def tageBisWartung(tageSeitLetzterWartung: Int): String = {
        val delta: Int = this.wartungsIntervall - tageSeitLetzterWartung

        delta match{
            case t: Int if t > 0 => s"Du hast noch ${t} Tage bis zur nächsten Wartung!"
            case t: Int if t == 0 => "Du musst heute zur Wartung!"
            case _ => s"Du bist ${-1* delta} überfällig!"
        }
    }
}

/**
 * Warum case classes so geil sind. 'mic drop'
 */
object exkursPatternMatching{
    val temp: Einzelteil = Einzelteil("cpu", 50)

    temp match {
        case Einzelteil("cpu", 50) => ???
        case Einzelteil(_, 50) => ???
        case Einzelteil("cpu", _) => ???
        case _ => ???
    }
}