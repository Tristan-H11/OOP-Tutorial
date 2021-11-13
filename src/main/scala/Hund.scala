class Hund extends Tier {
    override def lebtAnLand: Boolean = true

    override def isstFleisch: Boolean = true

    override def machEinenLaut: String = "Wuff"

    override def gewinntImKampfGegen(l1: Lebewesen): Boolean = {
        l1 match {
            case l1: Fisch if l1.istEinRiesenFisch => false
            case l1: Fisch => true
            case Klinger => false
            case l1: Mensch => false
        }
    }
}
