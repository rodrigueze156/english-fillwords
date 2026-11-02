@echo off
rem Запуск release.ps1 без изменения системной политики выполнения.
chcp 65001 >nul
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0release.ps1" %*
