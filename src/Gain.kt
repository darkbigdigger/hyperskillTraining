fun main() {
    printEmptyPole()
    var gameP = "---------"
    while(!winCheck(gameP)) {
        gameP = enterCoordinates(gameP, 'X')
        printGamePole(gameP)
        if (winCheck(gameP)) return
        gameP = enterCoordinates(gameP, 'O')
        printGamePole(gameP)
    }
}

fun enterCoordinates(gamePole: String, ch: Char): String {
    val pole = rowingString(gamePole)
    var str: String = ""
    print("Enter  coordinates:")
    var row: Int
    var column: Int
    while (true) {
        val lst = readln().split(" ")
        try {
            lst.map { it.toInt() }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            continue
        }

        try {
            row = lst.map { it.toInt() }[0]
            column = lst.map { it.toInt() }[1]

            if (row !in 1..3 || column !in 1..3) {
                println("Coordinates should be from 1 to 3!")
                continue
            }

            if (pole[row - 1][column - 1] == 'X' || pole[row - 1][column - 1] == 'O') {
                println("This cell is occupied! Choose another one!")
                continue
            }
            str = gamePole.substring(0, stringNumber(row, column)) + ch + gamePole.substring(
                stringNumber(
                    row,
                    column
                ) + 1, 9
            )
        }catch (e: IndexOutOfBoundsException){
            println("Please enter two numbers")
            continue
        }

        return str
    }


}

fun printEmptyPole() {
    println(
        """
           ---------
           |       |
           |       |
           |       |
           ---------
    """.trimIndent()
    )
}

fun stringNumber(x: Int, y: Int): Int {
    return when {
        (x == 1 && y == 1) -> 0
        (x == 1 && y == 2) -> 1
        (x == 1 && y == 3) -> 2
        (x == 2 && y == 1) -> 3
        (x == 2 && y == 2) -> 4
        (x == 2 && y == 3) -> 5
        (x == 3 && y == 1) -> 6
        (x == 3 && y == 2) -> 7
        (x == 3 && y == 3) -> 8
        else -> 0
    }
}


fun printGamePole(str: String) {

    println("---------")

    println("| ${str.slice(0 until 3).replace("", " ").trim().replace('-', ' ')} |")
    println("| ${str.slice(3 until 6).replace("", " ").trim().replace('-', ' ')} |")
    println("| ${str.slice(6 until 9).replace("", " ").trim().replace('-', ' ')} |")

    println("---------")

}

fun rowingString(str: String): List<String> {
    val row1 = str.slice(0 until 3)
    val row2 = str.slice(3 until 6)
    val row3 = str.slice(6 until 9)

    return listOf(row1, row2, row3)
}

fun winCheck(str: String): Boolean {
    var winCounter = 0
    val charList = listOf('X', 'O')
    val countX = str.count { it == 'X' }
    val countO = str.count { it == 'O' }
    var C = ' '

    val pole = rowingString(str)
    // row check
    for (i in pole.indices) {
        if (pole[i][0] in charList && pole[i][0] == pole[i][1] && pole[i][1] == pole[i][2]) {
            C = pole[i][0]
            winCounter += 1
        }
    }

    //column check
    for (i in pole.indices) {
        if (pole[0][i] in charList && pole[0][i] == pole[1][i] && pole[1][i] == pole[2][i]) {
            C = pole[0][i]
            winCounter += 1
        }
    }

    //main diagonal check
    if (pole[0][0] in charList && pole[0][0] == pole[1][1] && pole[1][1] == pole[2][2]) {
        C = pole[0][0]
        winCounter += 1
    }

    //anti-diagonal check
    if (pole[0][2] in charList && pole[0][2] == pole[1][1] && pole[1][1] == pole[2][0]) {
        C = pole[0][2]
        winCounter += 1
    }

    if (winCounter == 1) {
        println("$C wins")
        return true
    } else if (countX >= countO + 2 || countO > countX || winCounter > 1) {
        println("Impossible")
        return true
    } else if (winCounter < 1) {
        if (countX < 5) return false else {
            println("Draw")
            return true
        }

    }
    return false
}
