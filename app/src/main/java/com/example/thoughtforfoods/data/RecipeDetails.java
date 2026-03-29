package com.example.thoughtforfoods.data;

public class RecipeDetails {
    private String prepTime;
    private String cookTime;
    private String waitTime;
    private String totalTime;
    private String servings;

    public RecipeDetails(String prepTime, String cookTime, String waitTime, String totalTime, String servings) {
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.waitTime = waitTime;
        this.totalTime = totalTime;
        this.servings = servings;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

}
