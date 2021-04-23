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
        Scud missile = new Scud();
        radar.notice(missile);
        verify(batteryMock, times(batteryCount)).launchPatriot(missile);
    }

    @RepeatedTest(20)
    void whenMissiles_launchPatriot_once() {
        final int batteryCount = 1;
        BetterRadar radar = new BetterRadar(executorServiceMock, batteryMock, batteryCount);
        Scud missile = new Scud();
        radar.notice(missile);
        verify(batteryMock, times(batteryCount)).launchPatriot(missile);
    }

    @RepeatedTest(20)
    void whenMissiles_launchPatriot_tenTimes() {
        final int batteryCount = 10;
        BetterRadar radar = new BetterRadar(executorServiceMock, batteryMock, batteryCount);
        Scud missile = new Scud();
        radar.notice(missile);
        verify(batteryMock, times(batteryCount)).launchPatriot(missile);
    }
}
