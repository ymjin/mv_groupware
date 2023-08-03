package kr.movements.groupware.service;

import kr.movements.groupware.domain.BusinessCardImage;
import kr.movements.groupware.domain.Client;
import kr.movements.groupware.domain.Employee;
import kr.movements.groupware.domain.ProveImage;
import kr.movements.groupware.dto.ClientDto;
import kr.movements.groupware.repository.BusinessCardImageRepository;
import kr.movements.groupware.repository.ClientRepository;
import kr.movements.groupware.repository.EmployeeRepository;
import kr.movements.groupware.repository.ProveImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessCardImageService {
    private final BusinessCardImageRepository businessCardImageRepository;
    private final ClientRepository clientRepository;

    public void saveImage(Map<String, Object> param) {
        Optional<Client> clientOptional = clientRepository.findByName((String) param.get("name"));

        if (clientOptional.stream().count() != 0) {
            BusinessCardImage businessCardImage = new BusinessCardImage();
            businessCardImage.setOriginalName((String)param.get("file_name"));

            long curTime = System.currentTimeMillis();
            businessCardImage.setFileName(Long.toString(curTime));
            businessCardImage.setOriginalName((String) param.get("file_name"));
            businessCardImage.setImg((byte[]) param.get("file_byte"));
            businessCardImage.setFileSize((Long) param.get("file_size"));
            businessCardImage.setType((int) param.get("type"));
            businessCardImage.setClientId(clientOptional.get().getId());

            save(businessCardImage);
        }
    }

    @Transactional
    public Long save(BusinessCardImage businessCardImage) {

        businessCardImageRepository.save(businessCardImage);
        return businessCardImage.getId();
    }

    public List<BusinessCardImage> findByAllClientId(Long id) {
        return businessCardImageRepository.findAllByClientId(id);
    }

    public List<ClientDto> setImage(List<ClientDto> dto) {
        for (ClientDto clientDto:dto) {
            if (clientDto.getFront_id() != null) {
                Optional<BusinessCardImage> front = businessCardImageRepository.findById(Long.valueOf(clientDto.getFront_id()));

                if (front.stream().count() > 0) {
                    byte[] byteEnc64 = Base64.encodeBase64(front.get().getImg());
                    String imgStr = null;
                    try {
                        imgStr = new String(byteEnc64 , "UTF-8");
                        clientDto.setFront(imgStr);// .setImg(imgStr);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (clientDto.getRear_id() != null) {
                Optional<BusinessCardImage> rear = businessCardImageRepository.findById(Long.valueOf(clientDto.getRear_id()));

                if (rear.stream().count() > 0) {
                    byte[] byteEnc64 = Base64.encodeBase64(rear.get().getImg());
                    String imgStr = null;
                    try {
                        imgStr = new String(byteEnc64 , "UTF-8");
                        clientDto.setRear(imgStr);// .setImg(imgStr);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return dto;
    }
}
