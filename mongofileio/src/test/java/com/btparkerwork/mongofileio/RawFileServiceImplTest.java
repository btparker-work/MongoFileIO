package com.btparkerwork.mongofileio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.btparkerwork.mongofileio.model.RawFile;
import com.btparkerwork.mongofileio.repository.RawFileRepository;
import com.btparkerwork.mongofileio.service.RawFileServiceImpl;


@ExtendWith(SpringExtension.class)
public class RawFileServiceImplTest {

    @Mock
    private RawFileRepository repository;

    @InjectMocks
    private RawFileServiceImpl service;
/*
    @Test
    public void testSave() {
        RawFile file = new RawFile("test-id", "test-file", "image/jpeg", new byte[]{});
        when(repository.save(file)).thenReturn();
        RawFile savedFile = service.save(file);
        assertEquals(file, savedFile);
        verify(repository).save(file);
    }

    @Test
    public void testFindById() {
        RawFile file = new RawFile("test-id", "test-file", new byte[]{});
        when(repository.findById(file.getId())).thenReturn(file);
        RawFile foundFile = service.findById(file.getId());
        assertEquals(file, foundFile);
        verify(repository).findById(file.getId());
    }

    @Test
    public void testFindAll() {
        List<RawFile> files = Arrays.asList(
                new RawFile("id1", "file1", new byte[]{}),
                new RawFile("id2", "file2", new byte[]{})
        );
        when(repository.findAll()).thenReturn(files);
        List<RawFile> foundFiles = service.findAll();
        assertEquals(files, foundFiles);
        verify(repository).findAll();
    }

    @Test
    public void testFindAllByDateRange() {
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 31);
        Map<String, String> files = new HashMap<>();
        files.put("file1", "id1");
        files.put("file2", "id2");
        when(repository.getAllFilesByDateRange(startDate, endDate)).thenReturn(files);
        Map<String, String> foundFiles = service.findAllByDateRange(startDate, endDate);
        assertEquals(files, foundFiles);
        verify(repository).getAllFilesByDateRange(startDate, endDate);
    }
*/
}
