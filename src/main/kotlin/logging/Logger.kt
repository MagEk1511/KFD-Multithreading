package logging

import transactions.Status
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Logger(private val type: String): Observer {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    private val file = File("logs/log${LocalDateTime.now().format(formatter)}.txt")
    override fun update(status: Status, message: String) {
        file.appendText("[${LocalDateTime.now().format(formatter)}][$type] $status: $message\n")
    }
}