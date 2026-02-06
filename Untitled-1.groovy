#include <Arduino.h>
#include <DHT.h>

// --- CẤU HÌNH ---
#define DHTPIN 14     // Chân Data nối vào GPIO 14
#define DHTTYPE DHT11 // Loại cảm biến là DHT11

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  // Khởi động Serial Monitor tốc độ 115200
  Serial.begin(115200);
  Serial.println("--- KHOI DONG DHT11 ---");

  // Khởi động cảm biến
  dht.begin();
}

void loop() {
  // Chờ 2 giây giữa các lần đọc (DHT11 rất chậm)
  delay(2000);

  // Đọc độ ẩm (%)
  float h = dht.readHumidity();
  // Đọc nhiệt độ (độ C)
  float t = dht.readTemperature();

  // Kiểm tra lỗi (quan trọng)
  if (isnan(h) || isnan(t)) {
    Serial.println("Lỗi: Không tìm thấy cảm biến DHT11 !");
    Serial.println("-> Kiểm tra lại dây nối hoặc nguồn 3.3V/5V.");
    return;
  }

  // In kết quả ra màn hình
  Serial.print("Nhiet do: ");
  Serial.print(t);
  Serial.print("°C  |  Do am: ");
  Serial.print(h);
  Serial.println("%");
}
[env:esp32doit-devkit-v1]
platform = espressif32
board = esp32doit-devkit-v1
framework = arduino
monitor_speed = 115200

lib_deps =
    ; Thư viện cho DHT11
    adafruit/DHT sensor library @ ^1.4.4
    adafruit/Adafruit Unified Sensor @ ^1.1.9
    
    ; Thư viện cho BMP280 (Bạn đang thiếu cái này)
    adafruit/Adafruit BMP280 Library @ ^2.6.8
    
    ; Thư viện cho RFID RC522 (Bạn cũng đang thiếu cái này)
    miguelbalboa/MFRC522 @ ^1.4.10