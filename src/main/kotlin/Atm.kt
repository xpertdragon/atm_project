import kotlin.system.exitProcess

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var amount: Int = 0
)
var s = false
var message: String? = null
///var message1: String? = null
///var message2: String? = null
val users = mutableListOf<User>()

fun clearScreen() {
    print("\u001b[H\u001b[2J")
}


fun main() {

    while (true) {
        clearScreen()

        if (message == null) {
            println("Welcome to ATM!")
            println("How May I help you?")
        } else {
            println(message)
            message = null

        }
        println("----------------------------------------------------")
        println("1. REGISTER ACCOUNT")
        println("2. LOGIN")
        println("3. QUIT")
        println("----------------------------------------------------")
        print("Enter Selection: ")
        val choice = readln()
        clearScreen()

        if (choice == "1") {
            println("Lets Create Your Account")
            println("----------------------------------------------------")
            print("Enter First Name:")
            val firstName = readln()
            if(firstName.isNotEmpty()){
                print("Enter Last Name:")
                val lastName = readln()
                if(lastName.isNotEmpty()) {
                    print("Enter Email:")
                    val email = readln()
                    if (email.isNotEmpty()) {
                        if (email.contains("@") && email.contains(".")  && email.length >= 5) {

                            print("Enter Password:")
                            val password = readln()
                            if (password.isNotEmpty()) {
                                if(password.length >= 5) {
                                    print("Enter Initial Amount: ")
                                    var initialAmount = readln()
                                    if (initialAmount.isNotEmpty()) {
                                        if (Integer.parseInt(initialAmount) >= 0) {
                                            users.add(
                                                User(
                                                    firstName = firstName,
                                                    lastName = lastName,
                                                    email = email,
                                                    password = password,
                                                    amount = Integer.parseInt(initialAmount)
                                                )

                                            )
                                            message = "Account Registered Successfully"
                                        } else {
                                            message = "Enter a Valid Amount!"
                                        }
                                    } else {
                                        message = "Initial Amount is Empty"
                                    }
                                }else {
                                    message = "Password length must be greater than or equal to 5"
                                }
                            } else {
                                message = "Password is Empty"
                            }
                        } else {
                            message = "Invalid Email!"
                        }
                    }else {
                        message = "Email is Empty"
                    }
                }else {
                    message = "Last Name is Empty"
                }
            }else {
                message = "First Name is Empty"
            }

        } else if (choice == "2") {       ////   Login  Activity

            if (message == null) {
                println("Lets get You SigIn")
                println("-------------------------------------")
            } else {
                println(message)
            }

            println("Enter email: ")
            val email = readln()
            var a = -1
            var b = false
            for(i in users){
                a += 1
                if(i.email == email){
                    b =  true
                    break
                }
            }
            if(b){
                print("Enter Password: ")
                val password = readln()
                if(password.isNotEmpty()) {
                    if (users[a].password == password) {
                        val fn = users[a].firstName
                        val ln = users[a].lastName
                        clearScreen()
                        println("Welcome $fn $ln")
                        println("How can i assist you")

                        while (true) {
                            println("-----------------------------------")
                            println("1.Deposit Amount")
                            println("2.Withdraw Amount")
                            println("3.Check Amount")
                            println("4.Send Amount")
                            println("5.Logout")
                            println("----------------------------------------")
                            print("Enter Selection: ")
                            val choice = readln().toInt()
                            if (choice == 1) {              ///// to  deposit amount
                                clearScreen()
                                println("Let's add cash")
                                println("-------------------------------------")
                                print("Enter Amount to Deposit :")
                                val amount = readln()
                                if(amount.isNotEmpty()) {
                                    if (Integer.parseInt(amount) > 0) {
                                        users[a].amount += Integer.parseInt(amount)
                                        clearScreen()
                                        println("RS.$amount has been added to your accout!")
                                        println("your new balance is RS. " + (users[a].amount))
                                    } else {
                                        clearScreen()
                                        println("Please Enter a Valid Amount")
                                    }
                                }else {
                                    clearScreen()
                                    println("You didn't entered amount to deposit")
                                }
                            } else if (choice == 2) {       /////   to withdraw amount
                                clearScreen()
                                println("Let's withdraw cash")
                                println("-------------------------------------")
                                print("Enter Amount to Withdraw :")
                                val amountToW = readln().toInt()
                                if (amountToW > 0 && amountToW < (users[a].amount)) {
                                    users[a].amount -= amountToW
                                    clearScreen()
                                    println("You Withdraw RS.$amountToW")
                                    println("your new balance is RS." + users[a].amount)

                                } else {
                                    clearScreen()
                                    println("Invalid amount")
                                }

                            } else if (choice == 3) {       //////  to check balance
                                clearScreen()
                                println("Your balance is RS."+users[a].amount)

                            } else if (choice == 4) {       //////   to send balance
                                clearScreen()
                                println("Let's Send The Money")
                                println("---------------------------------")
                                println("Enter Recipient Email")
                                val receiverEmail = readln()
                                var s = false
                                var m = -1
                                for (l in users) {
                                    m += 1
                                    if (l.email == receiverEmail) {
                                        s = true
                                        break
                                    }
                                }
                                if(receiverEmail.isNotEmpty()) {
                                    if (s) {
                                        println("Enter amount to send: ")
                                        val amountToS = readln().toInt()
                                        if(amountToS.toString().isNotEmpty()) {
                                            if (amountToS < users[a].amount && amountToS > 0) {
                                                println("Enter your password: ")
                                                val myPassword = readln()
                                                if(myPassword.isNotEmpty()) {
                                                    if (myPassword == users[a].password) {
                                                        users[a].amount -= amountToS
                                                        users[m].amount += amountToS
                                                        clearScreen()
                                                        println("Rs." + amountToS + " has been sent to " + users[m].firstName+" "+ users[m].lastName)
                                                        println("Your new balance is Rs."+users[a].amount)
                                                    } else {
                                                        clearScreen()
                                                        println("password not matched")
                                                    }
                                                }else {
                                                    clearScreen()
                                                    println("your password is Empty")
                                                }
                                            } else {
                                                clearScreen()
                                                println("Insufficient Balance")

                                            }
                                        }else {
                                            clearScreen()
                                            println("You didn't enter amount to send")
                                        }

                                    } else {
                                        clearScreen()
                                        println("Account not found")
                                    }
                                }else {
                                    clearScreen()
                                    println("Receiver Email is empty")
                                }
                            } else if (choice == 5) {       //////    to  logout
                                clearScreen()
                                message = "Logout Success"
                                break
                            } else {
                                clearScreen()
                                println("Wrong Choice")
                            }

                        }

                    } else {
                        message = "Password not matched"
                    }
                }else{
                   message = "Password is Empty"
                }
            }else {

                message = "Email Not Found"
            }

        } else if (choice == "3") {
            clearScreen()
            println("Thanks For Using ")
            println("Tata! Bye! Bye!")
            break
        }

    }
}