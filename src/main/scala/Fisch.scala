class Fisch(riesenFisch: Boolean) extends Tier{
    override def lebtAnLand: Boolean = false

    override def isstFleisch: Boolean = riesenFisch //Große Fische essen Fleisch

    override def machEinenLaut: String = "Blub"

    override def gewinntImKampfGegen(l1: Lebewesen): Boolean = {
        if(riesenFisch && l1 != Klinger)
            true
        else
            false
    }

    def istEinRiesenFisch: Boolean = riesenFisch
}
