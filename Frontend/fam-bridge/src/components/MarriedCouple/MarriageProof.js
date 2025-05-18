import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function MarriageProof() {
    const [marriageProofUrl, setMarriageProofUrl] = useState(null);
    const [error, setError] = useState('');
    const { requestId } = useParams();

    useEffect(() => {
        fetchMarriageProof();
    }, []);

    const fetchMarriageProof = async () => {
        try {
            const response = await MarriedCoupleService.marriageProof(requestId);
    
            // Try to detect content type from response headers
            const contentType = response.headers['content-type'] || 'image/jpeg'; // fallback to jpeg
    
            const blob = new Blob([response.data], { type: contentType });
            const imageUrl = URL.createObjectURL(blob);
            setMarriageProofUrl(imageUrl);
    
        } catch (err) {
            console.error("Error fetching marriage proof:", err);
            setError("Failed to load marriage proof.");
        }
    };

    return (
        <section className="vh-100">
            <div className="container py-4 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-md-10" style={{justifyContent: 'center'}}>
                        <h2 style={{textAlign: 'center', color: '#B3817A', marginBottom: '2%', fontWeight:'bold'}}>Marriage Proof</h2><br></br>
                        {error && <div className="alert alert-danger">{error}</div>}

                        {marriageProofUrl ? (
                            <img
                                src={marriageProofUrl}
                                alt="Marriage Proof"
                                style={{ maxWidth: '100%', height: 'auto', display: 'block', margin: '0 auto' }}
                            />
                        ) : (
                        !error && <p>Loading...</p>
                        )}
                    </div>
                </div>
            </div>
        </section>
    );
}

export default MarriageProof;
