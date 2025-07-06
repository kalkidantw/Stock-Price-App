# 📈 Citi ICG Software Development Job Simulation – Stock Price Visualizer

This project was completed as part of the [Citi Institutional Clients Group (ICG) Technology Software Development Virtual Experience](https://www.theforage.com/virtual-internships/prototype/48c9K4HM6bhZy7mvG/Citi-Technology) hosted on Forage.

## 🧠 Task Overview

**Objective:** Build a Java application that tracks the Dow Jones Industrial Average (^DJI) stock price in real-time using Yahoo Finance, and visualizes the data in a live-updating line chart for internal Citi employees.

The simulation was broken down into two main goals:
1. Query stock prices every 5 seconds.
2. Visually display the queried stock data using a Java Swing line chart.

---

## 💻 Features

- Queries stock price every 5 seconds (interval can be adjusted).
- Queues stock data with timestamp and price.
- Displays a live-updating line chart (time vs. price).
- Built with standard Java libraries (Swing + YahooFinance API).

---

## ⚠️ API Limitation Notice

Yahoo Finance API currently returns a `403 Unauthorized` or `429 Too Many Requests` error due to restrictions on unauthenticated access.

As a result:
- The app runs, but no real stock data is shown unless a proxy or authentication layer is added.
- You may **enable mock data generation** to visualize the chart without hitting the API.

---

## 🧪 Mock Mode (for Testing the Chart)

You can replace the YahooFinance block with mock price generation:

```java
BigDecimal price = BigDecimal.valueOf(34000 + Math.random() * 1000); // Mock price
