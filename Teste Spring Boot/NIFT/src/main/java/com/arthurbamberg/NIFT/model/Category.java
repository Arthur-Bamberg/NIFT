package com.arthurbamberg.NIFT.model;

public class Category {
	private int idCategory;
    private String name, description;
    private boolean successfullyRecorded = false;

    public Category(int idCategory, String name, String description) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
    }

    public int getIdCategory() {
        return this.idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getSuccessfullyRecorded() {
        return successfullyRecorded;
    }
}
