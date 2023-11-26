import transactions.*
import java.util.*

class InterfaceUI(private val bank: Bank) {
    private val scanner = Scanner(System.`in`)

    fun start() {
        for (i in 1..3) {
            bank.addCashier(Cashier(bank, i))
        }

        while (true) {
            printMenu()
            when (scanner.nextInt()) {
                1 -> createTransactionMenu()
                2 -> bank.printClientInfo()
                3 -> bank.printCashierInfo()
                4 -> addClientMenu()
                5 -> {
                    bank.endCashiers()
                    bank.currencyHandler.endCurrencyHandler()
                    return
                }
                else -> println("Некорректный ввод. Пожалуйста, выберите правильный пункт.")
            }
        }
    }

    private fun printMenu() {
        println("\nВыберите действие:")
        println("1. Создать транзакцию")
        println("2. Вывести информацию о клиентах")
        println("3. Вывести информацию о кассирах")
        println("4. Добавить клиента")
        println("5. Выйти")
    }

    private fun createTransactionMenu() {
        println("\nВыберите тип транзакции:")
        println("1. Перевод средств")
        println("2. Внесение средств")
        println("3. Снятие средств")
        println("4. Перевод с обменом валюты")

        when (scanner.nextInt()) {
            1 -> {
                println("Введите данные для перевода средств:")
                println("Отправитель ID:")
                val senderId = scanner.nextInt()
                println("Получатель ID:")
                val receiverId = scanner.nextInt()
                println("Сумма:")
                val amount = scanner.nextDouble()
                val sourceClient = bank.getClient(senderId)
                val targetClient = bank.getClient(receiverId)
                if (sourceClient != null && targetClient != null && sourceClient.currency == targetClient.currency) {
                    bank.transactionsQueue.add(FundTransfer(sourceClient, targetClient, amount))
                } else {
                    println("Некорректные данные клиентов.")
                }
            }
            2 -> {
                println("Введите данные для внесения средств:")
                println("Клиент ID:")
                val clientId = scanner.nextInt()
                println("Сумма:")
                val amount = scanner.nextDouble()
                val client = bank.getClient(clientId)
                if (client != null) {
                    bank.transactionsQueue.add(Deposit(client, amount))
                } else {
                    println("Клиент не найден.")
                }
            }
            3 -> {
                println("Введите данные для снятия средств:")
                println("Клиент ID:")
                val clientId = scanner.nextInt()
                println("Сумма:")
                val amount = scanner.nextDouble()
                val client = bank.getClient(clientId)
                if (client != null) {
                    bank.transactionsQueue.add(Withdraw(client, amount))
                } else {
                    println("Клиент не найден.")
                }
            }
            4 -> {
                println("Введите данные для перевода с обменом валюты:")
                println("Отправитель ID:")
                val senderId = scanner.nextInt()
                println("Получатель ID:")
                val receiverId = scanner.nextInt()
                println("Сумма:")
                val amount = scanner.nextDouble()
                val sourceClient = bank.getClient(senderId)
                val targetClient = bank.getClient(receiverId)
                if (sourceClient != null && targetClient != null) {
                    bank.transactionsQueue.add(FundTransferWithExchange(sourceClient, targetClient, amount, bank))
                } else {
                    println("Некорректные данные клиентов.")
                }
            }
            else -> println("Некорректный ввод. Пожалуйста, выберите правильный пункт.")
        }
    }

    private fun addClientMenu() {
        try {
            println("\nВведите данные нового клиента:")
            println("ID:")
            val id = scanner.nextInt()
            println("Валюта:")
            val currency = Currency.getInstance(scanner.next())
            println("Начальный баланс:")
            val initialBalance = scanner.nextDouble()
            val newClient = Client(id, initialBalance, currency)
            bank.addClient(newClient)
            println("Клиент с ID $id добавлен успешно.")
        } catch (ex: Exception) {
            println("Некорректные данные клиентa.")
        }


    }
}
