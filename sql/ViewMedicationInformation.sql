--View for Prescription Information
create or replace view Medication_Info as
    select *
    from HealthCareManagement_Prescription;  

select *
from Medication_Info;
    
