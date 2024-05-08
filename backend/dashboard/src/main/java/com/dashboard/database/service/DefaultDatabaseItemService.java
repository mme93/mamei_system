package com.dashboard.database.service;


import com.dashboard.activity.items.model.basic.BasicItem;
import com.dashboard.activity.items.repository.BasicItemRepository;
import com.dashboard.activity.items.repository.CustomiseItemRepository;
import com.dashboard.activity.items.repository.TicketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefaultDatabaseItemService {

    private final BasicItemRepository basicItemRepository;
    private final TicketItemRepository ticketItemRepository;
    private final CustomiseItemRepository customiseItemRepository;

    @Autowired
    public DefaultDatabaseItemService(BasicItemRepository basicItemRepository, TicketItemRepository ticketItemRepository, CustomiseItemRepository customiseItemRepository) {
        this.basicItemRepository = basicItemRepository;
        this.ticketItemRepository = ticketItemRepository;
        this.customiseItemRepository = customiseItemRepository;
    }

    public void deleteDefaultDataset() {
    }

    public void deleteAllData() {
        basicItemRepository.deleteAll();
        ticketItemRepository.deleteAll();
        customiseItemRepository.deleteAll();
    }

    public List<Object> createDefaultDataSet() {
        List<Object>itemList=new ArrayList<>();
        if(!basicItemRepository.existsByName("Address")){
            basicItemRepository.save(new BasicItem(
                    "ADDRESS_SCHEME",
                    "Address",
                    "Save address from person.",
                    "contacts"
            ));
        }
        return itemList;
    }

}
