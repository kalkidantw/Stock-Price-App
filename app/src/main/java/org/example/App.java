package org.example;
import yahoofinance.Stock; // stock info
import yahoofinance.YahooFinance; // fetch from API
import java.io.IOException; // handles internet errors

import javax.swing.SwingUtilities;

import main.java.org.example.StockDashboard;


public class App {
    public static void main(String[] args) throws InterruptedException, IOException{

        String[] tickers = {"^DJI", "APPL", "GOOG"};
        
        for(int i = 0; i < tickers.length; i++){
            String currentTicker = tickers[i];

            try{
                Stock stock = YahooFinance.get(currentTicker); // fetch the stock object from Yahoo finannce

                if(stock != null){
                    // print basic information to the console
                    System.out.println("Ticker Symbol: " + stock.getSymbol());
                    System.out.println("Current Price: " + stock.getQuote().getPrice());
                    System.out.println("Day Change:    " + stock.getQuote().getChange());
                    System.out.println();
                }
                else{
                    System.out.println("Error, no data found for ticker: " + currentTicker);
                }

            }
            catch(IOException e){
                // handle any exceptions that occur during the fetch
                System.out.println("Failed to fetch data for ticker: " + currentTicker);
                System.out.println("Reason: " + e.getMessage());
                System.out.println();
            }
        }

        System.out.println("Finished fetching stock data.");
        SwingUtilities.invokeLater(() -> new StockDashboard());

    }
}
