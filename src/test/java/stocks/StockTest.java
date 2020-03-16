package stocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StockTest {

    public String expectedSymbol;
    public String expectedName;
    public String expectedInfo;
    public Stock test;
    public Transaction transaction;
    public TransactionMeta metaData;
    public String expectedDate = "_2020_03";
    public Double expectedOpen = 165.3100;
    public Double expectedHigh = 175.0000;
    public Double expectedLow = 138.5000;
    public Double expectedClose = 145.7000;
    public Integer expectedVolume = 636200296;
    public Integer expectedNumOfShares = 3;

    @Before
    public void init(){
        expectedSymbol = "msft";
        expectedName = "Microsoft";
        expectedInfo = "Tech company";
        metaData = TransactionMeta.TransactionMetaBuilder.newInstance()
                .setClose(expectedClose)
                .setDate(expectedDate)
                .setHigh(expectedHigh)
                .setLow(expectedLow)
                .setOpen(expectedOpen)
                .setVolume(expectedVolume)
                .build();
        transaction = new Transaction(metaData, expectedNumOfShares);
        test = new Stock(expectedSymbol, expectedName);
    }

    @Test
    public void constructorTest(){
        String actualSymbol = test.getSymbol();
        String actualName = test.getName();

        assertEquals(expectedSymbol, actualSymbol);
        assertEquals(expectedName, actualName);
    }

    @Test
    public void setGetInfo(){
        test.setInfo(expectedInfo);

        String actualInfo = test.getInfo();

        assertEquals(expectedInfo, actualInfo);
    }

    @Test
    public void getTotalNumSharesAndAddTest(){
        TransactionMeta diffMeta = TransactionMeta.TransactionMetaBuilder.newInstance()
                .setClose(expectedClose)
                .setDate(expectedDate)
                .setHigh(expectedHigh)
                .setLow(expectedLow)
                .setOpen(expectedOpen)
                .setVolume(expectedVolume)
                .build();
        Transaction diffTrans = new Transaction(diffMeta, 2);
        test.addTransaction(transaction);
        test.addTransaction(diffTrans);
        Integer expectedShares = 5;

        Integer actualShares = test.getTotalNumOfShares();

        assertEquals(expectedShares, actualShares);
    }

    @Test
    public void addTransactionFalse(){
        test.addTransaction(transaction);
        assertFalse(test.addTransaction(transaction));
    }

    @Test
    public void getTransactionTest(){
        test.addTransaction(transaction);

        Transaction actualTransaction = test.getTransaction(expectedDate);

        assertEquals(transaction, actualTransaction);
    }

    @Test
//    @Test(expected = NullPointerException.class)
    public void getTransactionTest2(){
        test.getTransaction(expectedDate);
    }
}
