package com.example.henrique.list.Utilidade_Publica.SchedulingCalculator;

import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import br.com.motiserver.dto.DailyScheduleDTO;
import br.com.motiserver.dto.PeriodDTO;
import br.com.motiserver.dto.ServiceDTO;

/**
 * Created by Cristor on 01/07/2015.
 */
public class FreeTimeCalculator {
    public static ArrayList<Integer> findFreeHours(List<PeriodDTO> periodDTOList, DailyScheduleDTO dailyScheduleDTO, List<ServiceDTO> selectedServices){
        ArrayList<Integer> freeHours = new ArrayList<>();
        System.out.println("Tamanho da periodDTOlist: " + periodDTOList.size());

        for (PeriodDTO periodDTO : periodDTOList){
            //um loop para cada periodo
            List<ServiceDTO> toCalculateService = new ArrayList<>();
            toCalculateService.addAll(selectedServices);
            System.out.println("Novo periodo");
            Calendar initialPeriodCal = periodDTO.getStartTime();
            Calendar finalPeriodCal = subtractSelectedServicesTime(periodDTO.getEndTime(), toCalculateService);
            initialPeriodCal.setTimeZone(TimeZone.getDefault());
            finalPeriodCal.setTimeZone(TimeZone.getDefault());
            System.out.println("Periodo de " + initialPeriodCal.get(Calendar.HOUR_OF_DAY) + ":"
                    + initialPeriodCal.get(Calendar.MINUTE) + " ate "
                    + finalPeriodCal.get(Calendar.HOUR_OF_DAY) + ":"
                    + finalPeriodCal.get(Calendar.MINUTE));
            System.out.println("Calendar hora inicial: " + initialPeriodCal.get(Calendar.HOUR_OF_DAY));

            //todo futuramente o servico do periodDTO, deve verificar se a data eh a mesma de hoje para nao trazer horas q jah passaram
            if(isToday(dailyScheduleDTO)){
                Calendar todayCal = Calendar.getInstance(TimeZone.getDefault());
                if(todayCal.after(initialPeriodCal)){
                    //se a data for de hoje, iniciar periodo com horario apos hora atual
                    initialPeriodCal.setTime(todayCal.getTime());
                    System.out.println("Este periodo inicia apos horario atual");
                }
            }

            if (finalPeriodCal.after(initialPeriodCal)){
                //verifica se o final do periodo eh de fato MAIOR que o inicio (apos subtrair tempo de servico selecionado)
                for (int i = initialPeriodCal.get(Calendar.HOUR_OF_DAY); i <= finalPeriodCal.get(Calendar.HOUR_OF_DAY); i++){
                    //um loop para cada hora verificando se a hora é menor ou igual ao final do periodo, para incluir na lista
                    System.out.println("Adicionado na lista, hora numero " + i);
                    freeHours.add(i);
                }
            }

        }
        System.out.println("Tamanho da Arraylist de horas: " + freeHours.size());

        return freeHours;
    }

    public static ArrayList<Integer> findFreeMinutes(int selectedHour, List<PeriodDTO> periodDTOList, List<ServiceDTO> selectedServices){
        ArrayList<Integer> freeMinutes = new ArrayList<>();
        System.out.println("Tamanho da periodDTOlist: " + periodDTOList.size());
        for (PeriodDTO periodDTO : periodDTOList){
            //um loop para cada periodo
            List<ServiceDTO> toCalculateService = new ArrayList<>();
            toCalculateService.addAll(selectedServices);

            Calendar initialPeriodCal = periodDTO.getStartTime();
            Calendar finalPeriodCal = subtractSelectedServicesTime(periodDTO.getEndTime(), toCalculateService);
            initialPeriodCal.setTimeZone(TimeZone.getDefault());
            finalPeriodCal.setTimeZone(TimeZone.getDefault());
            System.out.println("indice da lista de periodo do calendario: " + periodDTOList.lastIndexOf(periodDTO));
            System.out.println("Calendar hora do dia inicial: " + initialPeriodCal.get(Calendar.HOUR_OF_DAY));

            for (int i = initialPeriodCal.get(Calendar.HOUR_OF_DAY); i <= finalPeriodCal.get(Calendar.HOUR_OF_DAY); i++){
                //um loop para cada hora dentro do periodo
                System.out.println("Dentro da hora numero " + i);
                if(i == selectedHour) {
                    //verifica se a hora é a mesma q a selecionada
                    System.out.println("Encontrou hora igual a selecionada");
                    int initialMinutes = 0;
                    int finalMinutes = 55;
                    if (i == initialPeriodCal.get(Calendar.HOUR_OF_DAY)){
                        //verifica se a hora comeca na metade, caso sim, altera os minutos finais a serem apresentados
                        initialMinutes = initialPeriodCal.get(Calendar.MINUTE);
                        System.out.println("Hora selecionada coincide com inicio de um periodo");
                    }
                    if (i == finalPeriodCal.get(Calendar.HOUR_OF_DAY)){
                        //verifica se a hora termina na metade, caso sim, altera os minutos finais a serem apresentados
                        finalMinutes = finalPeriodCal.get(Calendar.MINUTE);
                        System.out.println("Hora selecionada coincide com final de um periodo");
                    }
                    while (initialMinutes <= finalMinutes) {
                        //um loop para cada 5 minutos
                        freeMinutes.add(initialMinutes);
                        initialMinutes = initialMinutes + 5;
                        System.out.println("Incluido na array o minuto " + initialMinutes);
                    }
                }
            }
        }
        System.out.println("Tamanho da Arraylist de minutos: " + freeMinutes.size());
        return freeMinutes;
    }

    public static boolean isFreeHourDay(List<PeriodDTO> periodDTOList, DailyScheduleDTO dailyScheduleDTO, List<ServiceDTO> selectedServices){
        //para verificar se o dia tem hora eh livre, precisa encontrar o servico mais rapido e ver se cabe em algum periodDTO
        ArrayList<ServiceDTO> newSelectedServicesList = new ArrayList<>();
        ArrayList<ServiceDTO> fasterServiceList = new ArrayList<>();
        newSelectedServicesList.addAll(selectedServices);
        long fasterServiceTime = newSelectedServicesList.get(0).getTime().getTimeInMillis();
        for(ServiceDTO fasterService : newSelectedServicesList){
            long toCompareServiceTime = fasterService.getTime().getTimeInMillis();
            if (toCompareServiceTime < fasterServiceTime){
                fasterServiceTime = toCompareServiceTime;
                fasterServiceList.clear();
                fasterServiceList.add(fasterService);
            }
        }
        //verifica freehour com uma array de apenas um servico (o menor deles)
        ArrayList<Integer> freeHours = new ArrayList<>();
        freeHours.addAll(findFreeHours(periodDTOList, dailyScheduleDTO, fasterServiceList));
        if(freeHours.size() <= 0){
            System.out.println("Este Dia nao tem horas livres");
            return false;
        } else {
            return true;
        }
    }
    public static boolean isToday(DailyScheduleDTO dailyScheduleDTO){
        if(DateUtils.isToday(dailyScheduleDTO.getDate().getTime().getTime())){
            System.out.println("Data selecionada eh hoje");
            return true;
        } else {
            return false;
        }
    }

    private static Calendar subtractSelectedServicesTime(Calendar finalPeriodCal, List<ServiceDTO> selectedServices){

        //esse metodo remove o tempo de servico selecionado pelo usuario do calendario recebido
        //para cada servico selecionado armazena tempo total
        long serviceTimeLong = 0;

        System.out.println("SelectedServices.size() = " + selectedServices.size());
        for (ServiceDTO serviceDTO : selectedServices){
            serviceTimeLong = serviceDTO.getTime().getTime().getTime() + serviceTimeLong;
            System.out.println("Somando tempos dos servicos selecionados:");
            System.out.println(serviceTimeLong + "=" + serviceDTO.getTime().getTime().getTime() + "+" + serviceTimeLong);

        }
        //todo verificar bug de fuso horario (e tirar esta correcao)
        serviceTimeLong = serviceTimeLong + (TimeZone.getDefault().getOffset(serviceTimeLong) * (selectedServices.size() - 1));

        Calendar subtractedCal = Calendar.getInstance();
        Calendar toSubtractCal = Calendar.getInstance();

        subtractedCal.setTime(finalPeriodCal.getTime());
        toSubtractCal.setTime(new Date(serviceTimeLong));
        subtractedCal.setTimeZone(TimeZone.getDefault());
        toSubtractCal.setTimeZone(TimeZone.getDefault());

        System.out.println(subtractedCal.get(Calendar.HOUR_OF_DAY) + ":" + subtractedCal.get(Calendar.MINUTE));
        System.out.println(-toSubtractCal.get(Calendar.HOUR_OF_DAY) + ":" + -toSubtractCal.get(Calendar.MINUTE));

        subtractedCal.add(Calendar.HOUR_OF_DAY, -toSubtractCal.get(Calendar.HOUR_OF_DAY));
        subtractedCal.add(Calendar.MINUTE, -toSubtractCal.get(Calendar.MINUTE));

        System.out.println(subtractedCal.get(Calendar.HOUR_OF_DAY) + ":" + subtractedCal.get(Calendar.MINUTE));

        selectedServices.clear();
        return subtractedCal;
    }
}
