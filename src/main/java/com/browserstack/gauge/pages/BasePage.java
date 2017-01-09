package com.browserstack.gauge.pages;

import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

public abstract class BasePage {
    protected static String Url = "https://www.google.com";

    public void storeStringToScenarioDataStore(String key, String value) {
        DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
        scenarioStore.put(key, value);
    }

    public void storeStringToSpecDataStore(String key, String value) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        specStore.put(key, value);
    }

    public void storeStringToSuiteDataStore(String key, String value) {
        DataStore suiteStore = DataStoreFactory.getSuiteDataStore();
        suiteStore.put(key, value);
    }

    public String fetchStringFromScenarioDataStore(String key) {
        DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
        return (String) scenarioStore.get(key);
    }

    public String fetchStringFromSpecDataStore(String key) {
        DataStore specStore = DataStoreFactory.getSpecDataStore();
        return (String) specStore.get(key);
    }

    public String fetchStringFromSuiteDataStore(String key) {
        DataStore suiteStore = DataStoreFactory.getSuiteDataStore();
        return (String) suiteStore.get(key);
    }
}
