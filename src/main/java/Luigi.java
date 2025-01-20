public class Luigi {
    public static void main(String[] args) {
        //ASCII Art of Chatbot's name
        String luigiLogo = "_____________________________________________\n" +
                " _        _    _    _____    _____    _____  \n" +
                "| |      | |  | |  |_   _|  / ____|  |_   _| \n" +
                "| |      | |  | |    | |   | |   _     | |   \n" +
                "| |      | |  | |    | |   | |  |_|    | |   \n" +
                "| |____  | |__| |   _| |_  | |__| |   _| |_  \n" +
                "|______|  \\____/   |_____|  \\_____|  |_____| \n" +
                "_____________________________________________";

        System.out.println(luigiLogo);

        //Greeting string of Chatbot
        String greeting = "Hello! I am Luigi!\nWhat can I do for you?\n" +
                "\n_____________________________________________";
        System.out.println(greeting);

        //Goodbye string of Chatbot
        String goodbye = "Goodbye! Hope to see you again soon!"+
                "\n_____________________________________________";

        System.out.println(goodbye);
    }
}
