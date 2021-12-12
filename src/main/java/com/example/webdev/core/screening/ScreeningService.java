package com.example.webdev.core.screening;



import com.example.webdev.core.screening.model.*;

import java.util.List;

public interface ScreeningService {
    void create(ScreeningDto screeningDto);

    void delete(ScreeningDto screeningDto);

    List<ScreeningDto> listScreenings();
}
