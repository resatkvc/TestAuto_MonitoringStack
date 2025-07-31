@echo off
echo Allure Results klasoru temizleniyor...
if exist "allure-results" (
    rmdir /s /q "allure-results"
    echo Allure Results klasoru basariyla silindi.
) else (
    echo Allure Results klasoru bulunamadi.
)
echo Temizlik tamamlandi.
pause 