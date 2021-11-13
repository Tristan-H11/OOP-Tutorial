trait Lebewesen {
    def lebtAnLand: Boolean

    def isstFleisch: Boolean

    def machEinenLaut: String

    def gewinntImKampfGegen(l1: Lebewesen): Boolean
}
