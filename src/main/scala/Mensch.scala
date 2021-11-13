class Mensch(fleischFresser: Boolean, groesse: Int, haarFarbe: String, name: String) extends Lebewesen{
    override def lebtAnLand: Boolean = true

    override def isstFleisch: Boolean = fleischFresser

    override def machEinenLaut: String = s"Guten Tag, ich bin der kleine $name."

    override def gewinntImKampfGegen(l1: Lebewesen): Boolean = {
        if (l1 == Klinger)
            false
        else
            true
    }
}
