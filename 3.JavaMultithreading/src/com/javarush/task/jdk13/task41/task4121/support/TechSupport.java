package com.javarush.task.jdk13.task41.task4121.support;

public abstract class TechSupport {

    private TechSupport nextLevel;

    public TechSupport setNextLevel(TechSupport nextLevel) {
        this.nextLevel = nextLevel;
        return this.nextLevel;
    }

    public abstract void solve(String problem);

    protected void passAlongTheChain(String problem) {
        if (nextLevel != null) {
            nextLevel.solve(problem);
        }
    }
        }