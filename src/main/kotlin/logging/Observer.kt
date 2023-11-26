package logging

import transactions.Status

interface Observer {
    fun update(status: Status, message: String)
}