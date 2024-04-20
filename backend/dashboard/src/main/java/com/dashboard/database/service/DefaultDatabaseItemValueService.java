package com.dashboard.database.service;

import com.dashboard.activity.items.model.EItemValueTyp;
import com.dashboard.activity.items.model.value.typ.ItemValueTyp;
import com.dashboard.activity.items.repository.ItemValueTypRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.dashboard.activity.items.model.EItemValueTyp.*;
import static java.util.Arrays.asList;

@Service
public class DefaultDatabaseItemValueService {

    private final ItemValueTypRepository itemValueTypRepository;

    @Autowired
    public DefaultDatabaseItemValueService(ItemValueTypRepository itemValueTypRepository) {
        this.itemValueTypRepository = itemValueTypRepository;
    }

    public List<ItemValueTyp> createDefaultDataSet() {
        List<ItemValueTyp> itemValueTypList = new ArrayList<>();
        List<EItemValueTyp> resultEItemValueList = itemValueTypRepository.findAll().stream().map(ItemValueTyp::getEItemValueTyp).toList();
        for (EItemValueTyp eItemValueTyp :
                asList(STRING, STRING_BLOCK, BOOLEAN, INT, FLOAT, LIST_BOOLEAN, LIST_INT, LIST_STRING)) {
            if (!resultEItemValueList.contains(eItemValueTyp)) {
                itemValueTypRepository.save(new ItemValueTyp(eItemValueTyp));
            }
        }
        return itemValueTypList;
    }

    public void deleteAllData() {
        itemValueTypRepository.deleteAll();
    }

    public void deleteDefaultDataset() {
        for (ItemValueTyp itemValueTyp : itemValueTypRepository.findAll()) {
            if (asList(STRING, STRING_BLOCK, BOOLEAN, INT, FLOAT, LIST_BOOLEAN, LIST_INT, LIST_STRING).contains(itemValueTyp.getEItemValueTyp())) {
                itemValueTypRepository.delete(itemValueTyp);
            }
        }
    }
}
