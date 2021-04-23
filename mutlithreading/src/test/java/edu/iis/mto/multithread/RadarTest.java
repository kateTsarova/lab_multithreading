package edu.iis.mto.multithread;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;
    @Mock
    private ExecutorService executorServiceMock;

    @BeforeEach
    void prepare() {
        when(executorServiceMock.submit(any(Runnable.class))).thenAnswer(invocation -> {
            ((Runnable) invocation.getArgument(0)).run();
            return null;
        });
    }

    @RepeatedTest(20)
    void whenNoMissiles_dontLaunchPatriots() {
        final int batteryCount = 0;
        BetterRadar radar = new BetterRadar(executorServiceMock, batteryMock, batteryCount);
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);
        verify(batteryMock, times(batteryCount)).launchPatriot(enemyMissile);
    }
}
