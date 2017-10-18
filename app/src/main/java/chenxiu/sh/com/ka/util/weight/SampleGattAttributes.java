/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chenxiu.sh.com.ka.util.weight;

import java.util.HashMap;

/**
 * This class includes a small subset of standard GATT attributes for
 * demonstration purposes.
 */
public class SampleGattAttributes {
    private static HashMap<String, String> attributes = new HashMap();

    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";

    public static String VTBLE_SCALE_SERVICE_UUID = "f433bd80-75b8-11e2-97d9-0002a5d5c51b";
    public static String VTBLE_SCALE_TEST_RESULT_CHARACTERISTIC_UUID = "1a2ea400-75b9-11e2-be05-0002a5d5c51b";
    public static String VTBLE_SCALE_SET_USER_CHARACTERISTIC_UUID = "29f11080-75b9-11e2-8bf6-0002a5d5c51b";
    public static String VTBLE_SCALE_USER_INFO_CHARACTERISTIC_UUID = "23b4fec0-75b9-11e2-972a-0002a5d5c51b";

    public static String VTBLE_IMMEDIATEALERT_SERVICE_UUID = "00001802-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_IMMEDIATE_ALERT_PROPERTY_UUID = "00002a06-0000-1000-8000-00805f9b34fb";

    public static String VTBLE_BATTERY_SERVICE_UUID = "0000180f-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_BATTERY_PROPERTY_UUID = "00002a19-0000-1000-8000-00805f9b34fb";

    public static String VTBLE_DEVICE_INFO_SERVICE_UUID = "0000180a-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_MANUFACTURE_NAME_PROPERTY_UUID = "00002a29-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_MODEL_NUMBER_PROPERTY_UUID = "00002a24-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_SERIAL_NUMBER_PROPERTY_UUID = "00002a25-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_HARDWARE_VERSION_PROPERTY_UUID = "00002a27-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_FIRMWARE_VERSION_PROPERTY_UUID = "00002a26-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_SOFTWARE_VERSION_PROPERTY_UUID = "00002a28-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_IEEE_PROPERTY_UUID = "00002a2a-0000-1000-8000-00805f9b34fb";
    public static String VTBLE_PNP_ID_PROPERTY_UUID = "00002a50-0000-1000-8000-00805f9b34fb";

    // VT Config
    public static String VTBLE_CONFIG_SERVICE_UUID = "78667579-0e7c-45ac-bb53-5279f8ee16fc";
    public static String VTBLE_DEVICE_NAME_PROPERTY_UUID = "78667579-db57-4c4a-8330-183d7d952170";
    public static String VTBLE_STOP_ALERT_PROPERTY_UUID = "78667579-5605-4f75-8e54-fceb7ea465a9";
    public static String VTBLE_LINKLOSS_TIMEOUT_PROPERTY_UUID = "78667579-d0fd-4b77-9515-d03224220c29";
    public static String VTBLE_PAIRED_KEY_PROPERTY_UUID = "78667579-8a38-4775-922d-85c5ccf921c0";
    public static String VTBLE_ADVERTISE_PROPERTY_UUID = "78667579-ae48-4e5b-ae14-b8eb728398ec";
    public static String VTBLE_IMMEDIATE_ALERT_TIMEOUT_PROPERTY_UUID = "78667579-e255-4c76-8a12-7be9b176e551";
    public static String VTBLE_DISCONNECT_ADVERTISE_PROPERTY_UUID = "78667579-5773-439a-bbcd-7672550a181b";
    public static String VTBLE_IBEACON_ADVERTISE_PROPERTY_UUID = "78667579-1149-4499-9e54-52e4e761ccd9";
    public static String VTBLE_IBEACON_INTERVAL_PROPERTY_UUID = "2a9246e4-6ed3-11e3-836d-d231feb1dc81";

    public static String VTBLE_TAG_COMMAND_SERVICE_UUID = "78667579-7b48-43db-b8c5-7928a6b0a335";
    public static String VTBLE_TAG_COMMAND_CHARACTERISTIC_UUID = "78667579-a914-49a4-8333-aa3c0cd8fedc";
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_HEADER_LEN = 2;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_FUNCTION = (byte) 0xFF;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_SUSPEND_DEVICE = 0x1;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_GOTO_BROADCAST = 0x2;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_SAVE_POWER = 0x3;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_FAST_SEND = 0x4;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_SET_TXPOWER = 0x5;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_SET_CONNECTION_PARAM = 0x6;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_TAG_LINKLOSS_SENSITIVITY = 0x7; // available
                                                                                      // for
                                                                                      // Tag
                                                                                      // device
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_PWM = 0x8; // available
                                                                 // for vtoy
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_LED = 0x9; // available
                                                                 // for vtoy
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_RGB_LED = 0xa; // available
                                                                     // for vtoy
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_SET_BROADCAST_INTERVAL = (byte) 0x80;
    public static byte BLE_TAG_COMMAND_CHARACTERISTIC_GROUP = (byte) 0xff;

    static {
        // Sample Services.
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate Service");
        // Sample Characteristics.
        attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");

        // Device Info Servcies
        attributes.put(VTBLE_DEVICE_INFO_SERVICE_UUID, "Device Information Service");
        attributes.put(VTBLE_MANUFACTURE_NAME_PROPERTY_UUID, "Manufacturer Name");
        attributes.put(VTBLE_MODEL_NUMBER_PROPERTY_UUID, "Model Number");
        attributes.put(VTBLE_SERIAL_NUMBER_PROPERTY_UUID, "Serial Number");
        attributes.put(VTBLE_HARDWARE_VERSION_PROPERTY_UUID, "Hardware Version");
        attributes.put(VTBLE_FIRMWARE_VERSION_PROPERTY_UUID, "Firmware Version");
        attributes.put(VTBLE_SOFTWARE_VERSION_PROPERTY_UUID, "Software Version");
        attributes.put(VTBLE_IEEE_PROPERTY_UUID, "IEEE");
        attributes.put(VTBLE_PNP_ID_PROPERTY_UUID, "Pnp Id");

        // VScale Services
        attributes.put(VTBLE_SCALE_SERVICE_UUID, "Smart Scale service");
        attributes.put(VTBLE_SCALE_TEST_RESULT_CHARACTERISTIC_UUID, "Smart Scale Test Result");
        attributes.put(VTBLE_SCALE_SET_USER_CHARACTERISTIC_UUID, "Smart Scale Select User");
        attributes.put(VTBLE_SCALE_USER_INFO_CHARACTERISTIC_UUID, "Smart Scale User Info");
        // VT Command
        attributes.put(VTBLE_TAG_COMMAND_SERVICE_UUID, "VT Command Service");
        attributes.put(VTBLE_TAG_COMMAND_CHARACTERISTIC_UUID, "VT Command Characteristic");

        // VT Config

        attributes.put(VTBLE_CONFIG_SERVICE_UUID, "VT Config Service");
        attributes.put(VTBLE_DEVICE_NAME_PROPERTY_UUID, "VT Device Name");
        attributes.put(VTBLE_STOP_ALERT_PROPERTY_UUID, "VT Stop Alert Setting");
        attributes.put(VTBLE_LINKLOSS_TIMEOUT_PROPERTY_UUID, "VT Linkloss Timeout");
        attributes.put(VTBLE_PAIRED_KEY_PROPERTY_UUID, "VT Paired Key");
        attributes.put(VTBLE_ADVERTISE_PROPERTY_UUID, "VT Advertise");
        attributes.put(VTBLE_IMMEDIATE_ALERT_TIMEOUT_PROPERTY_UUID, "VT Immediate Alert Timeout");
        attributes.put(VTBLE_DISCONNECT_ADVERTISE_PROPERTY_UUID, "VT Disconnect Advertise");
        attributes.put(VTBLE_IBEACON_ADVERTISE_PROPERTY_UUID, "VT Ibeacon Advertise");
        attributes.put(VTBLE_IBEACON_INTERVAL_PROPERTY_UUID, "VT Ibeacon Interval");

        // Immediate Alert
        attributes.put(VTBLE_IMMEDIATEALERT_SERVICE_UUID, "Immediate Alert Service");
        attributes.put(VTBLE_IMMEDIATE_ALERT_PROPERTY_UUID, "Immediate Alert Characteristic");
        // Battery
        attributes.put(VTBLE_BATTERY_SERVICE_UUID, "Battery Service");
        attributes.put(VTBLE_BATTERY_PROPERTY_UUID, "Battery Characteristic");

    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
