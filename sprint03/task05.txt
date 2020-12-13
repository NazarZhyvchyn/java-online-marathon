import java.util.function.ToDoubleFunction;

enum ClientType {
    NEW,
    SILVER(12){
        @Override
        public double discount() {
            return (100.0 - months * 0.25) / 100.0;
        }
    },
    GOLD(30) {
        @Override
        public double discount() {
            return (100.0 - months * 0.3) / 100.0;
        }
    },
    PLATINUM(60, ClientType::linearDiscountStrategy){
        @Override
        public double discount() {
            return (100.0 - months * 0.35) / 100.0;
        }
    };
    // *** fields ***
    private static int months;
    private final ToDoubleFunction<ClientType> discountStrategy;
    // *** constants ***
    private static final double PERCENTS_PER_MONTH_DISCOUNT = 0.35;
    // *** constructors ***
    ClientType(int months, ToDoubleFunction<ClientType> discountStrategy) {
        months = months;
        this.discountStrategy = discountStrategy;
    }
    ClientType(int months) {
        this(months, ClientType::linearDiscountStrategy);
    }
    ClientType() {
        this(0, ClientType::noDiscount);
    }
    // *** public methods ***
    public double discount() {
        return discountStrategy.applyAsDouble(this);
    }
    // *** discount strategies ***
    private static double linearDiscountStrategy(ClientType self) {
        return (100.0 - self.months * PERCENTS_PER_MONTH_DISCOUNT) / 100.0;
    }
    private static double noDiscount(ClientType self) {
        return 1.0;
    }
}