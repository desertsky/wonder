include ../make.config

# Set up a bunch of environment settings
SHELL = /bin/sh

INCLUDES = -I${APACHE2.4INCLUDE_DIR} -I../Adaptor -I/opt/sfw/includes

# Common compiler and linker flags
CFLAGS = -O2 ${INCLUDES} ${DEBUG_FLAG} -DSINGLE_THREADED_ADAPTOR -D${OS} -DFORKING_WEBSERVER -DAPACHE ${ENABLE_SSL_SUPPORT} 

APXSFLAGS = -c -S CC=${CC} -Wc,"${CFLAGS}" -Wl,"${LDFLAGS}"


ifeq "MACOS" "${OS}"

# These are the default settings for OSX.
CFLAGS += -Wall ${RC_CFLAGS} -g
LDARCHS = $(patsubst %, -arch %, ${RC_ARCHS})
LDFLAGS += ${OPENSSL_LIB_FLAGS} ${LDARCHS} -macosx_version_min 10.5 -lm -module
STRIP_FLAGS = -S
endif
# End MacOS X Specific Settings

ifeq "SOLARIS" "${OS}"
ENABLE_SSL_SUPPORT = -DAPACHE_SECURITY_ENABLED 
OPENSSL_INCL_FLAGS = -I/opt/sfw/include
OPENSSL_LIB_FLAGS = -L/opt/sfw/lib -lcrypto -lssl
LDFLAGS +=${OPENSSL_LIB_FLAGS}
CFLAGS = -O2 ${INCLUDES} ${DEBUG_FLAG} -DSINGLE_THREADED_ADAPTOR -D${OS} -DFORKING_WEBSERVER -DAPACHE ${ENABLE_SSL_SUPPORT}
APXSFLAGS = -c -S CC=${CC} -Wc,"${CFLAGS}" -Wl,"${LDFLAGS}"
endif

# Build steps
# Get the list of source files from the ../Adaptor directory and change their extension to .o
COMMON_SRCFILES := $(wildcard ../Adaptor/*.c)
#COMMON_OBJFILES := $(patsubst %.c, %.o, $(wildcard ../Adaptor/*.c))
COMMON_OBJFILES := $(patsubst ../Adaptor/%.c, %.o, $(wildcard ../Adaptor/*.c))
# The same list as above but with the .lo extension (libtool object, FWIW)
lo_objects = $(patsubst ../Adaptor/%.c, %.lo, $(wildcard ../Adaptor/*.c))


all: mod_WebObjects.so

# The objects target compiles all the .c files, except mod_WebObjects.c, which we want
# libtool (through apxs or otherwise) to take care of.
#$(COMMON_OBJFILES) : %.o : ../Adaptor/%.c
#	$(CC) $(CFLAGS) -c $<

mod_WebObjects.so : mod_WebObjects.c ${COMMON_SRCFILES}
	${APXS2.4} ${APXSFLAGS} mod_WebObjects.c ${COMMON_SRCFILES}
#	${APXS2.4} -i -A -n WebObjects mod_WebObjects.la #auto-install module into libexec dir
	cp .libs/mod_WebObjects.so .  #extra copy to put mod_WebObjects.so in traditional location


clean:
	rm -f mod_WebObjects.slo mod_WebObjects.so mod_WebObjects.o *.o
	rm -f ../Adaptors/*.o ../*.slo ../*.lo
	rm -rf .libs
		
include ../Adaptor/make.postamble



