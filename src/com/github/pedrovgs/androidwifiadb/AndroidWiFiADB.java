package com.github.pedrovgs.androidwifiadb;

import com.github.pedrovgs.androidwifiadb.adb.ADB;
import java.util.List;

public class AndroidWiFiADB {

  private final ADB adb;
  private final View view;

  public AndroidWiFiADB(ADB adb, View view) {
    this.adb = adb;
    this.view = view;
  }

  public void connectDevices() {
    if (!isADBInstalled()) {
      view.showADBNotInstalledNotification();
      return;
    }
    List<Device> devices = adb.getDevicesConnectedByUSB();
    if (devices.isEmpty()) {
      view.showNoConnectedDevicesNotification();
      return;
    }

    devices = adb.connectDevices(devices);
    for (Device device : devices) {
      if (device.isConnected()) {
        view.showConnectedDeviceNotification(device);
      } else {
        view.showErrorConnectingDeviceNotification(device);
      }
    }
  }

  private boolean isADBInstalled() {
    return adb.isInstalled();
  }
}
