object Main {

    def koennenSichBegegnen(l1: Lebewesen, l2: Lebewesen): Boolean = {
        l1.lebtAnLand == l2.lebtAnLand
    }

    def istEinTier(l1: Lebewesen): Boolean = {
        l1.isInstanceOf[Tier]
    }

    def main(args: Array[String]): Unit = {
        val fisch: Fisch = new Fisch(false)
        val ganzRiesenFisch: Fisch = new Fisch(true)
        val hund: Hund = new Hund
        val lucas: Mensch = new Mensch(true, 185, "Dunkelblond", "Lucas")

        println("Fisch ist ein Tier: " + istEinTier(fisch))
        println("Lucas ist ein Tier: " + istEinTier(lucas))
        println("Hund und Fisch können sich begegnen: " + koennenSichBegegnen(hund, fisch))
        println()
        println("Fisch: " + fisch.machEinenLaut)
        println("Hund: " + hund.machEinenLaut)
        println("Lucas: " + lucas.machEinenLaut)
        println("Klinger: " + Klinger.machEinenLaut)
        println()
        println("Hund gewinnt gegen Fisch: " + hund.gewinntImKampfGegen(fisch))
        println("Hund gewinnt gegen riesen Fisch: " + hund.gewinntImKampfGegen(ganzRiesenFisch))
        println()
        println("Fisch gewinnt gegen riesen Fisch: " + fisch.gewinntImKampfGegen(ganzRiesenFisch))
        println("Fisch gewinnt gegen Lucas: " + fisch.gewinntImKampfGegen(lucas))
        println("Riesen Fisch gewinnt im Kampf gegen Lucas: " + ganzRiesenFisch.gewinntImKampfGegen(lucas))
        println("Fisch würde Lucas essen: " + fisch.isstFleisch)
        println("Riesen Fisch würde Lucas essen: " + ganzRiesenFisch.isstFleisch)
        println("Lucas würde einen der Fische essen: " + lucas.isstFleisch)
        println()
        println("Klinger gewinnt im Kampf gegen Lucas: " + Klinger.gewinntImKampfGegen(lucas))
        println("Riesen Fisch gewinnt im Kampf gegen Klinger: " + ganzRiesenFisch.gewinntImKampfGegen(Klinger))
    }

    //    laufen
    //    atmen
    //    fliegen
    //    könnenKämpfen
    //    Gehirnzellen

}
