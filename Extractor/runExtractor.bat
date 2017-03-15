@echo off

echo Enter path to Hybris Dir
set INPUT=
set /P INPUT=Type input: %=%

echo %INPUT%

set orgDir="%CD%"

cd %INPUT%\bin\platform

call setantenv.bat
call ant extensionsxml

cd %orgDir%

java com.sap.yupgradeinfoext.UpgradeInfoExtractor %INPUT%



