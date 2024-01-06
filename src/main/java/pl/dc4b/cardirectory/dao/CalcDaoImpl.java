package pl.dc4b.cardirectory.dao;

public class CalcDaoImpl implements CalcDao {
    @Override
    public int addTwoNumbers(int x, int y) {
        return x + y;
    }

    @Override
    public void sayHello() {
        System.out.println("Hello from CalcDao!");
    }
}
