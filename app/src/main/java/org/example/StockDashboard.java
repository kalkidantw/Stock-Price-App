package main.java.org.example;

import org.jfree.chart.ChartFactory; // creates standard chart types
import org.jfree.chart.ChartPanel; // swing panel that holds the chart
import org.jfree.chart.JFreeChart; // the actual chart object
import org.jfree.chart.plot.XYPlot; // the plot part of the chart
import org.jfree.data.time.Second; // for x-axis (time ticks in seconds)
import org.jfree.data.time.TimeSeries; // data series with time-based values
import org.jfree.data.time.TimeSeriesCollection; // dataset holding all time series
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*; // GUI components like JFrame, etc.
import java.awt.*; // for layout and window sizing
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class StockDashboard extends JFrame {

    private TimeSeries stockSeries; // this will hold (time, price) points for the chart

    public StockDashboard() {
        super("Dow Jones Live Stock Price");

        stockSeries = new TimeSeries("DJI"); // create an empty time series


        // this collection can hold multiple time series (we only need one for now)
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(stockSeries);

        // Build the chart from the dataset
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Live DJI Price",
                "Time",
                "Price (USD)",
                dataset,
                false, // Legend? No, we only have one line.
                true, // Tooltips enabled
                false // No URLs
        );

        // Customize plot appearance if needed (e.g., colors, gridlines)
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // creates a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Add the chart panel to the JFrame
        setContentPane(chartPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // size the window to fit the contents
        setLocationRelativeTo(null); // center the window
        setVisible(true); // show the window

                // scheduled task to fetch stock data every 5 seconds
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Fetch the stock object for Dow Jones (^DJI)
                Stock stock = YahooFinance.get("^DJI");
        
                // Get the current price
                BigDecimal price = stock.getQuote().getPrice();
        
                // Get the current timestamp (to plot against time)
                Second current = new Second();
        
                // Update the series (this will redraw the chart)
                stockSeries.addOrUpdate(current, price);
        
                // Log to console
                System.out.println("[" + current + "] $" + price);
            } 
                    
            catch (IOException e) {
                System.out.println("Failed to fetch stock data.");
            }
                
        }, 0, 5, TimeUnit.SECONDS); // start immediately, then every 5 seconds
        
    }
}



