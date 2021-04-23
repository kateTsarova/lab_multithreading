package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BetterRadar {
    private final ExecutorService executorService;
    private final PatriotBattery patriotBattery;
    private final int rocket;

    public BetterRadar(PatriotBattery patriotBattery, int rocket) {
        this.executorService = Executors.newSingleThreadExecutor();
        this.patriotBattery = patriotBattery;
        this.rocket = rocket;
    }

    public BetterRadar(ExecutorService executorService, PatriotBattery patriotBattery, int rocket) {
        this.executorService = executorService;
        this.patriotBattery = patriotBattery;
        this.rocket = rocket;
    }

    public void notice(Scud missile) {
        launchPatriot(missile, rocket);
    }

    private void launchPatriot(Scud missile, int rocket) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocket; i++) {
                patriotBattery.launchPatriot(missile);
            }
        };
        executorService.submit(launchPatriotTask);
    }
}