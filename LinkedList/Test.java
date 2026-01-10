class Test {

    // !This worked only up to Java 6
    static {
        System.out.println("Hello from static block");
        System.exit(0);
    }

    // ! Since Java 7, the JVM requires a valid main method to launch an application.
    // ! Without main, you will get:
    // ! Error: Main method not found in class Test
    static {
        System.out.println("Hello from static block");
    }

    public static void main(String[] args) {
        System.exit(0);
    }
}
