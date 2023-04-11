FROM php:8.0-apache

COPY ./ /var/www/html
RUN mv "$PHP_INI_DIR/php.ini-development" "$PHP_INI_DIR/php.ini"
RUN sed -i 's/allow_url_include = Off//g' "$PHP_INI_DIR/php.ini"
RUN echo "allow_url_include = On" >> "$PHP_INI_DIR/php.ini"

# Latest release PHP Composer
COPY --from=composer/composer:latest-bin /composer /usr/bin/composer

# Clear PHP Dependency to reinstall for docker
RUN apt update && apt install unzip

# Run composer
RUN cd /var/www/html && rm -r vendor/composer && composer update

# Install LDAP Dependencies
#RUN apt-get update && apt-get install -y \
#    libldap2-dev
#RUN docker-php-ext-configure ldap --with-libdir=lib/x86_64-linux-gnu/
#RUN docker-php-ext-install ldap
#RUN echo "extension=php_ldap.so" >> "$PHP_INI_DIR/php.ini"

#EXPOSE 8080

RUN echo "ServerName localhost" >> /etc/apache2/apache2.conf
RUN service apache2 restart
