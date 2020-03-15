package account;

import org.decimal4j.util.DoubleRounder;
import stocks.StockEnum;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<StockEnum,Integer> portfolio;
    private Double buyingPower;
    private Double currentPortfolioValue;

    public Portfolio() {
        this.portfolio = new HashMap<StockEnum, Integer>();
        this.buyingPower = 2500.00;
        this.currentPortfolioValue = 0.00;
    }

    public boolean addStockToBasePortfolio(StockEnum stock, Integer numberOfShares){
        double costOfShares = stock.getOpen() * numberOfShares;
        if (costOfShares < buyingPower){
            if(portfolio.containsKey(stock)){
                portfolio.replace(stock, portfolio.get(stock),portfolio.get(stock) + numberOfShares);
            } else {
                portfolio.put(stock,numberOfShares);
            }
            currentPortfolioValue = currentPortfolioValue + DoubleRounder.round((costOfShares),2);
            buyingPower = DoubleRounder.round((buyingPower - costOfShares),2);
            return true;
        } else {
            return false;
        }
    }

    public double getBuyingPower() {
        return buyingPower;
    }

    public double getCurrentPortfolioValue() {
        return currentPortfolioValue;
    }




}
