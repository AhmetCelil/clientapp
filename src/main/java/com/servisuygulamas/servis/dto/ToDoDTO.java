package com.servisuygulamas.servis.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ToDoDTO
{
    private Long id;
    private String yapicalacakIs;
    private String sure;
    private String tarih;
}
