import logging.Logger

import java.lang.management.ManagementFactory


fun main() {
    val bank = Bank()
    bank.addObserver(Logger("Bank"))
    bank.currencyHandler.addObserver(Logger("Currency"))
    val interfaceUI = InterfaceUI(bank)
    interfaceUI.start()
}