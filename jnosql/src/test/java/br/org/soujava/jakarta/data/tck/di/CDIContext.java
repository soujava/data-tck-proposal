package br.org.soujava.jakarta.data.tck.di;

import jakarta.enterprise.context.spi.CreationalContext;

class CDIContext implements DIContext {

    private final CreationalContext<Object> context;

    private CDIContext(CreationalContext<Object> context) {
        this.context = context;
    }

    @Override
    public void release() {
        this.context.release();
    }

    static CDIContext of(CreationalContext<Object> context){
        return new CDIContext(context);
    }

}
