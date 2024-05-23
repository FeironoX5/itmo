:: Скрипт для подключения к Helios
:: 1. Задайте перменные среды HELIOS_LOGIN и HELIOS_PW
:: 2. Зайдите в настройки терминала и создайте новый профиль
:: 3. Укажите этот файл как используемый в профиле

:: Запустить WinSCP для передачи файлов
call helios_scp.bat

:: Задать кодировку UTF8
chcp 65001

:: Подключиться к Helios используя перменные среды HELIOS_LOGIN и HELIOS_PW
%SystemRoot%\System32\cmd.exe -cmd /K plink -ssh helios.cs.ifmo.ru -P 2222 -l %HELIOS_LOGIN% -pw %HELIOS_PW%