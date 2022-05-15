fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns = readln().toInt()
    var cinemaSeats = MutableList(rows) { MutableList(columns) { 'S' } }
    while (true) {
        println(
            """
          1. Show the seats
          2. Buy a ticket
          0. Exit
      """.trimIndent()
        )
        when (readln().toInt()) {
            1 -> {
                printCinemaHall(cinemaSeats, rows, columns)
                continue
            }
            2 -> {
                cinemaSeats = ticketPrice(cinemaSeats, (rows * columns), rows)
                continue
            }
            0 -> break
        }
    }

}

fun ticketPrice(gamePole: MutableList<MutableList<Char>>, totalSeats: Int, rows: Int): MutableList<MutableList<Char>> {
    println("Enter a row number:")
    val selectedRow = readln().toInt()


    println("Enter a seat number in that row:")
    val selectedSeat = readln().toInt()

    val ticketPrice: Int
    if (totalSeats > 60) {
        ticketPrice = if (selectedRow > rows / 2) 8 else 10
        print("Ticket price: $$ticketPrice")

    } else {
        ticketPrice = 10
        println("Ticket price: $$ticketPrice")
    }

    gamePole[selectedRow - 1][selectedSeat - 1] = 'B'
    return gamePole
}

fun printCinemaHall(gamePole: MutableList<MutableList<Char>>, rows: Int, columns: Int) {
    println("Cinema:")

    for (i in 0..rows) {
        if (i == 0) {
            print(" ")
            repeat(columns) {
                print(" ${it + 1}")
            }
            println()
            continue
        }
        print(i)
        for (j in 1..columns) {
            print(" ${gamePole[i - 1][j - 1]}")
        }
        println()
    }

}