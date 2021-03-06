/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.axonframework.samples.trader.tradeengine.command;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.axonframework.samples.trader.api.orders.trades.CreateBuyOrderCommand;
import org.axonframework.samples.trader.api.orders.trades.CreateOrderBookCommand;
import org.axonframework.samples.trader.api.orders.trades.CreateSellOrderCommand;

/**
 * @author Allard Buijze
 */
public class OrderBookCommandHandler {

    private Repository<OrderBook> repository;

    @CommandHandler
    public void handleBuyOrder(CreateBuyOrderCommand command) {
        OrderBook orderBook = repository.load(command.getOrderBookId(), null);

        orderBook.addBuyOrder(command.getOrderId(),
                command.getTransactionId(),
                command.getTradeCount(),
                command.getItemPrice(),
                command.getPortfolioId());
    }

    @CommandHandler
    public void handleSellOrder(CreateSellOrderCommand command) {
        OrderBook orderBook = repository.load(command.getOrderBookId(), null);
        orderBook.addSellOrder(command.getOrderId(),
                command.getTransactionId(),
                command.getTradeCount(),
                command.getItemPrice(),
                command.getPortfolioId());
    }

    @CommandHandler
    public void handleCreateOrderBook(CreateOrderBookCommand command) {
        OrderBook orderBook = new OrderBook(command.getOrderBookIdentifier());
        repository.add(orderBook);
    }

    public void setRepository(Repository<OrderBook> orderBookRepository) {
        this.repository = orderBookRepository;
    }
}
