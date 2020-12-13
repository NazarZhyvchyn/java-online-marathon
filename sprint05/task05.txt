class InsufficientAmountException extends Exception {
    double amount;
    public InsufficientAmountException(double needs) {
        amount = needs;
    }

    public double getAmount() {
        return amount;
    }
     @Override
    public String getMessage() {
        return String.format("Sorry, but you are short $%s", amount);
    }
}