#!/usr/bin/expect -f
log_file="logs/commands.log"
> "$log_file"
exec > >(tee -a "$log_file") 2>&1
#plink.exe -ssh swarch@xhgrid1 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid2 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid3 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid4 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid5 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid6 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid7 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid8 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid9 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid10 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid11 -pw swarch -m clientRun.sh  &
#plink.exe -ssh swarch@xhgrid12 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid13 -pw swarch -m clientRun.sh  &
#plink.exe -ssh swarch@xhgrid14 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid15 -pw swarch -m clientRun.sh  &
#plink.exe -ssh swarch@xhgrid16 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid17 -pw swarch -m clientRun.sh  &
#plink.exe -ssh swarch@xhgrid18 -pw swarch -m clientRun.sh  &
#plink.exe -ssh swarch@xhgrid19 -pw swarch -m clientRun.sh  &
#plink.exe -ssh swarch@xhgrid20 -pw swarch -m clientRun.sh  &  
plink.exe -ssh swarch@xhgrid21 -pw swarch -m clientRun.sh  &
plink.exe -ssh swarch@xhgrid22 -pw swarch -m clientRun.sh  &
exec >&-