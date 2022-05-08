package br.com.releasesolutions.services;

import br.com.releasesolutions.models.User;

public interface EmailService {

    void notifyDelay(User user);
}
